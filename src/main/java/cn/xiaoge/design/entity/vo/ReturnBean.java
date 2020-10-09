package cn.xiaoge.design.entity.vo;

import lombok.Data;

/**
 * 返回前端对象
 */
@Data
public class ReturnBean {
    private Integer code;
    private String msg;
    private Object obj;
    private Boolean ok;

    private ReturnBean(Integer code, String msg, Object data, boolean ok) {
        this.code = code;
        this.msg = msg;
        this.obj = data;
        this.ok = ok;
    }

    public static ReturnBean of(AnswerCode answerCode, Object obj) {
        return new ReturnBean(answerCode.code, answerCode.msg, obj, answerCode.code == 200);
    }

    public static ReturnBean of(AnswerCode answerCode) {
        return new ReturnBean(answerCode.code, answerCode.msg, null, answerCode.code == 200);
    }

    private ReturnBean() {
    }

    public enum AnswerCode {
        //SUCCESS 成功
        SUCCESS("操作成功", 200),
        UNKNOWN_ERROR("未知错误", 500),
        PARAMETER_ERROR("参数错误", 400),
        NOT_LOGIN("未经授权", 401),
        AUTH_TIMEOUT("资源过期", 410),
        ADD_ERROR("添加出错", 402),
        NOT_PERMISSION("服务器已拒绝", 403),
        OBJECT_NOT_EXIST("对象不存在", 404),
        METHOD_NOT_SUPPORTED("方法不被支持", 405),
        UPDATE_ERROR("修改出错", 406),
        DELETE_ERROR("删除出错", 407);

        private Integer code;
        private String msg;

        AnswerCode(String msg, Integer code) {
            this.msg = msg;
            this.code = code;
        }
    }

}

