package com.kalessil.phpStorm.phpInspectionsEA.inspectors.apiUsage.fileSystem;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.*;
import com.kalessil.phpStorm.phpInspectionsEA.fixers.UseSuggestedReplacementFixer;
import com.kalessil.phpStorm.phpInspectionsEA.openApi.BasePhpElementVisitor;
import com.kalessil.phpStorm.phpInspectionsEA.openApi.BasePhpInspection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/*
 * This file is part of the Php Inspections (EA Extended) package.
 *
 * (c) Vladimir Reznichenko <kalessil@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

public class RealpathInStreamContextInspector extends BasePhpInspection {
    private static final String messageUseDirname = "'realpath(...)' works differently in a stream context (e.g., for phar://...). Consider using 'dirname(...)' instead.";
    private static final String patternUseDirname = "'%s' should be used instead (due to how realpath handles streams).";

    @NotNull
    public String getShortName() {
        return "RealpathInStreamContextInspection";
    }

    @Override
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new BasePhpElementVisitor() {
            @Override
            public void visitPhpFunctionCall(@NotNull FunctionReference reference) {
                final String functionName = reference.getName();
                // new pattern ` =! ...`
                if (functionName != null && functionName.equals("realpath")) {
                    final PsiElement[] arguments = reference.getParameters();
                    if (arguments.length == 1 && !this.isTestContext(reference)) {
                        this.analyze(reference, arguments[0]);
                    }
                }
            }

            private void analyze(@NotNull FunctionReference reference, @NotNull PsiElement subject) {
                /* case 1: include/require context */
                /* get parent expression through () */
                PsiElement parent = reference.getParent();
                while (parent instanceof ParenthesizedExpression) {
                    parent = parent.getParent();
                }
                if (parent instanceof Include) {
                    final String replacement = generateReplacement(subject);
                    if (replacement == null) {
                        holder.registerProblem(reference, messageUseDirname);
                    } else {
                        holder.registerProblem(
                                reference,
                                String.format(patternUseDirname, replacement),
                                new SecureRealpathFix(replacement)
                        );
                    }
                    return;
                }

                /* case 2: realpath applied to a relative path '..' */
                final Collection<StringLiteralExpression> literals = PsiTreeUtil.findChildrenOfType(reference, StringLiteralExpression.class);
                if (!literals.isEmpty()) {
                    for (final StringLiteralExpression literal : literals) {
                        if (literal.getContents().contains("..")) {
                            final String replacement = generateReplacement(subject);
                            if (replacement == null) {
                                holder.registerProblem(reference, messageUseDirname);
                            } else {
                                holder.registerProblem(
                                        reference,
                                        String.format(patternUseDirname, replacement),
                                        new SecureRealpathFix(replacement)
                                );
                            }
                            break;
                        }
                    }
                    literals.clear();
                }
            }
        };
    }

    @Nullable
    private static String generateReplacement(@NotNull PsiElement subject) {
        String replacement = null;

        if (subject instanceof ConcatenationExpression) {
            final ConcatenationExpression concat = (ConcatenationExpression) subject;
            final PsiElement left                = concat.getLeftOperand();
            if (
                null != left && !(left instanceof ConcatenationExpression) &&
                concat.getRightOperand() instanceof StringLiteralExpression
            ) {
                final StringLiteralExpression right = (StringLiteralExpression) concat.getRightOperand();
                final String rightContent           = right.getContents();
                if (rightContent.startsWith("/..")) {
                    final String quote = right.isSingleQuote() ? "'" : "\"";

                    final StringBuilder newLeft = new StringBuilder(left.getText());
                    String newRight             = rightContent;
                    while (newRight.startsWith("/..")) {
                        newRight = newRight.replaceFirst("/\\.\\.", "");
                        newLeft.insert(0, "dirname(").append(')');
                    }

                    replacement = newLeft + " . " + quote + newRight + quote;
                }
            }
        }

        if (subject instanceof StringLiteralExpression) {
            replacement = subject.getText();
        }

        return replacement;
    }

    private static final class SecureRealpathFix extends UseSuggestedReplacementFixer {
        private static final String title = "Secure this realpath(...)";

        @NotNull
        @Override
        public String getName() {
            return title;
        }

        SecureRealpathFix(@NotNull String expression) {
            super(expression);
        }
    }
}