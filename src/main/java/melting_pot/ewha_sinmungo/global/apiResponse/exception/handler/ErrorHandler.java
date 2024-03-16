package melting_pot.ewha_sinmungo.global.apiResponse.exception.handler;


import melting_pot.ewha_sinmungo.global.apiResponse.code.BaseErrorCode;
import melting_pot.ewha_sinmungo.global.apiResponse.exception.GeneralException;

public class ErrorHandler extends GeneralException {

    public ErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
