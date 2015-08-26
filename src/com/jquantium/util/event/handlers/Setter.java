package com.jquantium.util.event.handlers;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by Mykhailo_Bohdanov on 26/08/2015.
 */
public class Setter {

    @AroundInvoke
    public Object broadcast(InvocationContext context) throws Exception {

        context.getTarget();

        return context.proceed();
    }

}
