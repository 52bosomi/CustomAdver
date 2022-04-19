package com.ad.ADtest.dto;

import lombok.Data;

@Data
public class Restful {

    private static String data;
    private boolean isError = false;
    private String reason;

    public Restful Error(String reason){this.reason = reason; this.isError = true; return this;};
    public Restful Data(String data){this.data = data; return this;};
}
