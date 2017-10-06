package com.jex.aad.error;

import android.content.res.Resources;
import android.text.TextUtils;

import com.jex.aad.AAd;
import com.jex.aad.R;

/**
 * API接口，异常错误处理
 * <p>
 * <pre>
 * 需要在string.xml中添加错误描述,以 code+错误代码 的形式出现
 * </pre>
 */
public class AAdException extends Exception {

    private static final long serialVersionUID = -6055929793464170833L;
    private String error;
    private String oriError;
    private String error_code;

    public AAdException() {

    }

    public AAdException(String detailMessage) {
        error = detailMessage;
    }

    public AAdException(String detailMessage, Throwable throwable) {
        error = detailMessage;
    }

    private String getError() {

        String result;

        if (!TextUtils.isEmpty(error)) {
            result = error;
        } else {

            String name = "code" + error_code;
            int i = AAd.app.getResources().getIdentifier(name, "string", AAd.app.getPackageName());

            try {
                result = AAd.app.getString(i);

            } catch (Resources.NotFoundException e) {

                if (!TextUtils.isEmpty(oriError)) {
                    result = oriError;
                } else {
                    result = AAd.app.getString(R.string.arad_unknown_error_code) + error_code;
                }
            }
        }

        return result;
    }

    @Override
    public String getMessage() {
        return getError();
    }

    /**
     * 设置错误代码
     *
     * @param error_code
     */
    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    /**
     * 如果没有错误代码，可以抛出原声的错误信息
     *
     * @param oriError
     */
    public void setOriError(String oriError) {
        this.oriError = oriError;
    }

}
