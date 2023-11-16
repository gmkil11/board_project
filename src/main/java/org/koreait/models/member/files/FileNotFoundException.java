package org.koreait.models.member.files;

import oracle.jdbc.proxy.oracle$1jdbc$1driver$1AbstractShardingCallableStatement$2oracle$1jdbc$1internal$1OracleCallableStatement$$$Proxy;
import org.koreait.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends CommonException {
    public FileNotFoundException() {

        super(bundleError.getString("NotFound.file"), HttpStatus.BAD_REQUEST);
    }
}
