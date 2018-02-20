package com.kalessil.phpStorm.phpInspectionsEA.inspectors.apiUsage.deprecations;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.php.config.PhpLanguageLevel;
import com.jetbrains.php.config.PhpProjectConfigurationFacade;
import com.jetbrains.php.lang.psi.elements.FunctionReference;
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression;
import com.jetbrains.php.util.PhpStringUtil;
import com.kalessil.phpStorm.phpInspectionsEA.openApi.BasePhpElementVisitor;
import com.kalessil.phpStorm.phpInspectionsEA.openApi.BasePhpInspection;
import com.kalessil.phpStorm.phpInspectionsEA.utils.ExpressionSemanticUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/*
 * This file is part of the Php Inspections (EA Extended) package.
 *
 * (c) Vladimir Reznichenko <kalessil@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

public class DynamicCallsToScopeIntrospectionInspector extends BasePhpInspection {
    private static final String messagePattern = "Produces runtime warning (cannot call %s() dynamically).";

    final private static Map<String, Integer> targetCalls        = new HashMap<>();
    final private static Map<String, Integer> callbacksPositions = new HashMap<>();
    static {
        targetCalls.put("compact",         -1);
        targetCalls.put("extract",         -1);
        targetCalls.put("func_get_args",    0);
        targetCalls.put("func_get_arg",     1);
        targetCalls.put("func_num_args",    0);
        targetCalls.put("get_defined_vars", 0);
        targetCalls.put("mb_parse_str",     1);
        targetCalls.put("parse_str",        1);
        callbacksPositions.put("call_user_func",       0);
        callbacksPositions.put("call_user_func_array", 0);
        callbacksPositions.put("array_filter",         1);
        callbacksPositions.put("array_map",            0);
        callbacksPositions.put("array_walk",           1);
        callbacksPositions.put("array_walk_recursive", 1);
    }

    @NotNull
    public String getShortName() {
        return "DynamicCallsToScopeIntrospectionInspection";
    }

    @Override
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new BasePhpElementVisitor() {
            @Override
            public void visitPhpFunctionCall(@NotNull FunctionReference reference) {
                /* http://php.net/manual/en/migration71.incompatible.php#migration71.incompatible.forbid-dynamic-calls-to-scope-introspection-functions */
                final PhpLanguageLevel php = PhpProjectConfigurationFacade.getInstance(holder.getProject()).getLanguageLevel();
                if (php.compareTo(PhpLanguageLevel.PHP710) >= 0) {
                    final String functionName = reference.getName();
                    if (functionName != null && callbacksPositions.containsKey(functionName)) {
                        final int callbackPosition   = callbacksPositions.get(functionName);
                        final PsiElement[] arguments = reference.getParameters();
                        if (arguments.length >= callbackPosition + 1) {
                            final PsiElement argument             = arguments[callbackPosition];
                            final StringLiteralExpression literal = ExpressionSemanticUtil.resolveAsStringLiteral(argument);
                            if (literal != null) {
                                /* TODO: replicate to uniqid */
                                final String raw      = PhpStringUtil.unescapeText(literal.getContents(), literal.isSingleQuote());
                                final String callback = raw.startsWith("\\") ? raw.substring(1) : raw;
                                if (targetCalls.containsKey(callback)) {
                                    /* TODO: report */
                                }
                            }
                        }
                    }
                    /* TODO: variable function -> [\]targetCall + arguments count */
                }
            }
        };
    }
}