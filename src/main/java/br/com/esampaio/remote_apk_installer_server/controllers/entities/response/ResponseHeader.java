package br.com.esampaio.remote_apk_installer_server.controllers.entities.response;

public class ResponseHeader {
    public Integer status;
    public String errorMessage;

    public ResponseHeader() {
    }
    public ResponseHeader(Integer status) {
        this(status,null);
    }
    public ResponseHeader(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
