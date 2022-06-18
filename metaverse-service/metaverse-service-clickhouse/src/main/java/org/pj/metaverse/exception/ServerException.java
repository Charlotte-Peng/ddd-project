package org.pj.metaverse.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pj.metaverse.result.DataResult;

/**
 * Description:
 *
 * @author pj
 * @date 2021/5/26/026 18:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ServerException extends RuntimeException {
    private Integer code;
    private String message;
    private Object data;

    public ServerException(DataResult<Void> dataResult){
        this.code = dataResult.getCode();
        this.message = (String) dataResult.getMessage();
    }
}
