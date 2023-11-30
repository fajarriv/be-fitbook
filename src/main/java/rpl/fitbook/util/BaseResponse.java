package rpl.fitbook.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BaseResponse {
    Integer code;
    Object data;
    String status;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> errors;
}
