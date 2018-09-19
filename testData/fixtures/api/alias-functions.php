<?php

    echo <warning descr="'is_double(...)' is an alias function, consider using 'is_float(...)' instead.">is_double</warning>();
    echo <warning descr="'is_integer(...)' is an alias function, consider using 'is_int(...)' instead.">is_integer</warning>();
    echo <warning descr="'is_long(...)' is an alias function, consider using 'is_int(...)' instead.">is_long</warning>();
    echo <warning descr="'is_real(...)' is an alias function, consider using 'is_float(...)' instead.">is_real</warning>();
    echo <warning descr="'sizeof(...)' is an alias function, consider using 'count(...)' instead.">sizeof</warning>();
    echo <warning descr="'doubleval(...)' is an alias function, consider using 'floatval(...)' instead.">doubleval</warning>();
    echo <warning descr="'fputs(...)' is an alias function, consider using 'fwrite(...)' instead.">fputs</warning>();
    echo <warning descr="'join(...)' is an alias function, consider using 'implode(...)' instead.">join</warning>();
    echo <warning descr="'key_exists(...)' is an alias function, consider using 'array_key_exists(...)' instead.">key_exists</warning>();
    echo <warning descr="'chop(...)' is an alias function, consider using 'rtrim(...)' instead.">chop</warning>();
    echo <warning descr="'ini_alter(...)' is an alias function, consider using 'ini_set(...)' instead.">ini_alter</warning>();
    echo <warning descr="'is_writeable(...)' is an alias function, consider using 'is_writable(...)' instead.">is_writeable</warning>();
    echo <warning descr="'pos(...)' is an alias function, consider using 'current(...)' instead.">pos</warning>();
    echo <warning descr="'show_source(...)' is an alias function, consider using 'highlight_file(...)' instead.">show_source</warning>();
    echo <warning descr="'strchr(...)' is an alias function, consider using 'strstr(...)' instead.">strchr</warning>();
    echo <warning descr="'set_file_buffer(...)' is an alias function, consider using 'stream_set_write_buffer(...)' instead.">set_file_buffer</warning>();
    echo <warning descr="'session_commit(...)' is an alias function, consider using 'session_write_close(...)' instead.">session_commit</warning>();
    echo <warning descr="'mysqli_escape_string(...)' is an alias function, consider using 'mysqli_real_escape_string(...)' instead.">mysqli_escape_string</warning>();
    echo <warning descr="'recode(...)' is an alias function, consider using 'recode_string(...)' instead.">recode</warning>();
    echo <warning descr="'socket_getopt(...)' is an alias function, consider using 'socket_get_option(...)' instead.">socket_getopt</warning>();
    echo <warning descr="'socket_setopt(...)' is an alias function, consider using 'socket_set_option(...)' instead.">socket_setopt</warning>();
    echo <warning descr="'openssl_get_privatekey(...)' is an alias function, consider using 'openssl_pkey_get_private(...)' instead.">openssl_get_privatekey</warning>();
    echo <warning descr="'posix_errno(...)' is an alias function, consider using 'posix_get_last_error(...)' instead.">posix_errno</warning>();
    echo <warning descr="'ldap_close(...)' is an alias function, consider using 'ldap_unbind(...)' instead.">ldap_close</warning>();
    echo <warning descr="'pcntl_errno(...)' is an alias function, consider using 'pcntl_get_last_error(...)' instead.">pcntl_errno</warning>();
    echo <warning descr="'ftp_quit(...)' is an alias function, consider using 'ftp_close(...)' instead.">ftp_quit</warning>();
    echo <warning descr="'odbc_do(...)' is an alias function, consider using 'odbc_exec(...)' instead.">odbc_do</warning>();
    echo <warning descr="'socket_set_blocking(...)' is an alias function, consider using 'stream_set_blocking(...)' instead.">socket_set_blocking</warning>();
    echo <warning descr="'stream_register_wrapper(...)' is an alias function, consider using 'stream_wrapper_register(...)' instead.">stream_register_wrapper</warning>();
    echo <warning descr="'socket_set_timeout(...)' is an alias function, consider using 'stream_set_timeout(...)' instead.">socket_set_timeout</warning>();
    echo <warning descr="'socket_get_status(...)' is an alias function, consider using 'stream_get_meta_data(...)' instead.">socket_get_status</warning>();
    echo <warning descr="'diskfreespace(...)' is an alias function, consider using 'disk_free_space(...)' instead.">diskfreespace</warning>();
    echo <warning descr="'odbc_field_precision(...)' is an alias function, consider using 'odbc_field_len(...)' instead.">odbc_field_precision</warning>();
    echo <warning descr="'mysqli_execute(...)' is an alias function, consider using 'mysqli_stmt_execute(...)' instead.">mysqli_execute</warning>();

    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 7.0.0.">magic_quotes_runtime</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.4.0. Relying on this alias is highly discouraged.">ocifreecursor</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_bind_param</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_bind_result</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_client_encoding</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_fetch</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_param_count</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_get_metadata</warning>();
    echo <warning descr="This alias has been DEPRECATED as of PHP 5.3.0 and REMOVED as of PHP 5.4.0.">mysqli_send_long_data</warning>();