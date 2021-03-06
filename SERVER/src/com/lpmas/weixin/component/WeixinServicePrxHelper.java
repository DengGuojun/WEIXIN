// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.1
//
// <auto-generated>
//
// Generated from file `weixin.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.lpmas.weixin.component;

public final class WeixinServicePrxHelper extends Ice.ObjectPrxHelperBase implements WeixinServicePrx
{
    private static final String __rpc_name = "rpc";

    public String rpc(String method, String params)
    {
        return rpc(method, params, null, false);
    }

    public String rpc(String method, String params, java.util.Map<String, String> __ctx)
    {
        return rpc(method, params, __ctx, true);
    }

    private String rpc(String method, String params, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        __checkTwowayOnly(__rpc_name);
        return end_rpc(begin_rpc(method, params, __ctx, __explicitCtx, true, null));
    }

    public Ice.AsyncResult begin_rpc(String method, String params)
    {
        return begin_rpc(method, params, null, false, false, null);
    }

    public Ice.AsyncResult begin_rpc(String method, String params, java.util.Map<String, String> __ctx)
    {
        return begin_rpc(method, params, __ctx, true, false, null);
    }

    public Ice.AsyncResult begin_rpc(String method, String params, Ice.Callback __cb)
    {
        return begin_rpc(method, params, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_rpc(String method, String params, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_rpc(method, params, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_rpc(String method, String params, Callback_WeixinService_rpc __cb)
    {
        return begin_rpc(method, params, null, false, false, __cb);
    }

    public Ice.AsyncResult begin_rpc(String method, String params, java.util.Map<String, String> __ctx, Callback_WeixinService_rpc __cb)
    {
        return begin_rpc(method, params, __ctx, true, false, __cb);
    }

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_rpc(method, params, null, false, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_rpc(method, params, null, false, false, __responseCb, __exceptionCb, __sentCb);
    }

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     java.util.Map<String, String> __ctx, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb)
    {
        return begin_rpc(method, params, __ctx, true, false, __responseCb, __exceptionCb, null);
    }

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     java.util.Map<String, String> __ctx, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                     IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_rpc(method, params, __ctx, true, false, __responseCb, __exceptionCb, __sentCb);
    }

    private Ice.AsyncResult begin_rpc(String method, 
                                      String params, 
                                      java.util.Map<String, String> __ctx, 
                                      boolean __explicitCtx, 
                                      boolean __synchronous, 
                                      IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                      IceInternal.Functional_BoolCallback __sentCb)
    {
        return begin_rpc(method, params, __ctx, __explicitCtx, __synchronous, 
                         new IceInternal.Functional_TwowayCallbackArg1<String>(__responseCb, __exceptionCb, __sentCb)
                             {
                                 public final void __completed(Ice.AsyncResult __result)
                                 {
                                     WeixinServicePrxHelper.__rpc_completed(this, __result);
                                 }
                             });
    }

    private Ice.AsyncResult begin_rpc(String method, 
                                      String params, 
                                      java.util.Map<String, String> __ctx, 
                                      boolean __explicitCtx, 
                                      boolean __synchronous, 
                                      IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__rpc_name);
        IceInternal.OutgoingAsync __result = getOutgoingAsync(__rpc_name, __cb);
        try
        {
            __result.prepare(__rpc_name, Ice.OperationMode.Normal, __ctx, __explicitCtx, __synchronous);
            IceInternal.BasicStream __os = __result.startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(method);
            __os.writeString(params);
            __result.endWriteParams();
            __result.invoke();
        }
        catch(Ice.Exception __ex)
        {
            __result.abort(__ex);
        }
        return __result;
    }

    public String end_rpc(Ice.AsyncResult __iresult)
    {
        IceInternal.OutgoingAsync __result = IceInternal.OutgoingAsync.check(__iresult, this, __rpc_name);
        try
        {
            if(!__result.__wait())
            {
                try
                {
                    __result.throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.startReadParams();
            String __ret;
            __ret = __is.readString();
            __result.endReadParams();
            return __ret;
        }
        finally
        {
            if(__result != null)
            {
                __result.cacheMessageBuffers();
            }
        }
    }

    static public void __rpc_completed(Ice.TwowayCallbackArg1<String> __cb, Ice.AsyncResult __result)
    {
        com.lpmas.weixin.component.WeixinServicePrx __proxy = (com.lpmas.weixin.component.WeixinServicePrx)__result.getProxy();
        String __ret = null;
        try
        {
            __ret = __proxy.end_rpc(__result);
        }
        catch(Ice.LocalException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        catch(Ice.SystemException __ex)
        {
            __cb.exception(__ex);
            return;
        }
        __cb.response(__ret);
    }

    public static WeixinServicePrx checkedCast(Ice.ObjectPrx __obj)
    {
        return checkedCastImpl(__obj, ice_staticId(), WeixinServicePrx.class, WeixinServicePrxHelper.class);
    }

    public static WeixinServicePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __ctx, ice_staticId(), WeixinServicePrx.class, WeixinServicePrxHelper.class);
    }

    public static WeixinServicePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return checkedCastImpl(__obj, __facet, ice_staticId(), WeixinServicePrx.class, WeixinServicePrxHelper.class);
    }

    public static WeixinServicePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        return checkedCastImpl(__obj, __facet, __ctx, ice_staticId(), WeixinServicePrx.class, WeixinServicePrxHelper.class);
    }

    public static WeixinServicePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        return uncheckedCastImpl(__obj, WeixinServicePrx.class, WeixinServicePrxHelper.class);
    }

    public static WeixinServicePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        return uncheckedCastImpl(__obj, __facet, WeixinServicePrx.class, WeixinServicePrxHelper.class);
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::com::lpmas::weixin::component::WeixinService"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    public static void __write(IceInternal.BasicStream __os, WeixinServicePrx v)
    {
        __os.writeProxy(v);
    }

    public static WeixinServicePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            WeixinServicePrxHelper result = new WeixinServicePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
