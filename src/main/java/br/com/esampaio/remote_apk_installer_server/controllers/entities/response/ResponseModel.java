package br.com.esampaio.remote_apk_installer_server.controllers.entities.response;

public class ResponseModel<RM> {
    public ResponseHeader header;
    public RM response;

    public ResponseModel(ResponseHeader header, RM response) {
        this.header = header;
        this.response = response;
    }
}
