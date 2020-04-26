package com.zgl.aftersales.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private Boolean status=false;
    private Object data=null;
    private String msg;

}
