package com.cyanrocks.oms.controller.base;

import org.springframework.beans.factory.annotation.Value;

public class BaseController {
    @Value("${control.saveOrder:true}")
    protected boolean controlSaveOrder;
}
