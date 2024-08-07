package com.smilemall.basic.kakaopay;

import lombok.ToString;

/**
 * Created by kakaopay
 */

// 결제요청 응답 클래스
@ToString
public class ReadyResponse {
    private String tid;							//결제 고유번호
    private String created_at;					//결제준비 요청시간
    private String next_redirect_pc_url;		//요청한 클라이언트가 pc웹일 경우
    

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNext_redirect_pc_url() {
        return next_redirect_pc_url;
    }

    public void setNext_redirect_pc_url(String next_redirect_pc_url) {
        this.next_redirect_pc_url = next_redirect_pc_url;
    }


}
