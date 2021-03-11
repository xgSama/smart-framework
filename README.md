# smart-framework
《架构探险》 轻量级的Java Web框架

## 框架的初始化
`com.xgsama.framework.HelpLoader.init()`
1. ClassHelper：获取应用包下的所有类的Set集合`CLASS_SET`
2. BeanHelper：从`CLASS_SET`中取出带有某注解的类，利用反射初始化后加入`BEAN_MAP`中
3. AopHelper：获取到代理类和目标类之间的映射关系，并用代理类替换`BEAN_MAP`中对应的值
4. IocHelper：初始化IOC容器，通过`@Inject`注解通过类型从`BEAN_MAP`中获取值后通过反射赋值
5. ControllerHelper：通过`@Action`注解获取Request与Handler之间的映射关系

## AOP的实现
### AOP初始化过程
1. 获取代理类及其目标类集合之间的映射关系
    在`CLASS_SET`中获取所有`AspectProxy`类的子类（这些都是代理类），然后根据这些类的`@Aspect`注解的值去从`CLASS_SET`中获取他要代理的类的Set集合`targetClassSet`，以代理类为key目标类为value创建`proxyMap`
    
2. 获取目标类与代理类实例之间的映射关系
    遍历`proxyMap`,通过反射创建代理类的实例`proxy`，然后遍历`targetClassSet`；以目标类targetClass为key，List<Proxy>为value创建一个`targetMap`；将`proxy`添加到`targetMap`的value中（`targetMap.get(targetClass).add(proxy)`）
    
3. 使用`cglib`构建代理对象

    通过`targetMap`的key(targetClass)和value(proxyList)创建代理对象

    `Object proxy = ProxyManager.createProxy(targetClass, proxyList);`

4. 覆盖掉`BEAN_MAP`中原有的value

   `BeanHelper.setBean(targetClass, proxy);`



AopHelper要在IocHelper之前加载，因为首先要通过AopHelper获取代理对象然后才能通过IocHelper进行依赖注入