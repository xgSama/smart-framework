package com.xgsama.framework.proxy;

/**
 * Proxy
 *
 * @author xgSama
 * @date 2021/1/15 20:16
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
