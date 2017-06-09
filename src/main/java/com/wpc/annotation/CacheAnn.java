/**
 * 项目名：duia-web-admin <br>
 * 包名：com.duia.common.bean.annotation <br>
 * 文件名：CacheAnnotation.java <br>
 * 版本信息：TODO <br>
 * 作者：赵增斌 E-mail：zhaozengbin@gmail.com QQ:4415599 weibo:http://weibo.com/zhaozengbin<br>
 * 日期：2017年3月15日-下午2:03:03<br>
 * Copyright (c) 2017 赵增斌-版权所有<br>
 *
 */
package com.wpc.annotation;

import com.wpc.enums.ECacheDataSource;
import com.wpc.enums.ECachePrefix;

import java.lang.annotation.*;

/**
 *
 * 类名称：CacheAnn <br>
 * 类描述：缓存公用注解 <br>
 * 创建人：赵增斌 <br>
 * 修改人：赵增斌 <br>
 * 修改时间：2017年3月15日 下午2:03:03 <br>
 * 修改备注：TODO <br>
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheAnn {

	/**
	 * 方法：customKey <br>
	 * 描述：自定义key,可为空,如果为空则默认按照入口参数名及值来生成key <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月23日 下午1:54:02 <br>
	 *
	 * @return
	 */
	String customKey() default "";

	/**
	 * 方法：appointFieldKey <br>
	 * 描述：指定作为KEY的字段,如果为空则把全部入口参数作为key <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月23日 下午2:00:18 <br>
	 *
	 * @return
	 */
	String[] appointFieldKey() default "";

	/**
	 * 方法：groupKey <br>
	 * 描述：分组key,必选,用来指定此缓存针对的模块,在isClean参数为true时则直接清空groupKey为组的所有缓存
	 * 只有clean为true为数组,为false时为字符
	 * 可支持多个,只要符合其中一个就进行删除,如果groupKey被{}修饰且为入口参数名,则用相应的入口参数值来代替作为组key,删除时同理,如{groupKey}<br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月23日 下午2:04:24 <br>
	 *
	 * @return
	 */
	String[] groupKey();

	/**
	 * 方法：cacheTime <br>
	 * 描述：缓存时间 单位：秒 默认5分钟 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月23日 下午2:02:26 <br>
	 *
	 * @return
	 */
	int cacheTime() default 5;

	/**
	 * 方法：isClean <br>
	 * 描述：是否为清除操作,如果此值为true 则直接按照groupKey进行清除,并不执行缓存操作,用于数据变动后清空相关缓存的 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月23日 下午2:06:09 <br>
	 *
	 * @return
	 */
	boolean isClean() default false;

	/**
	 * 方法：eCacheDataSource <br>
	 * 描述：缓存数据源 目前只支持区分题库 <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年3月23日 下午2:19:54 <br>
	 *
	 * @return
	 */
	ECacheDataSource eCacheDataSource();

	/**
	 * 方法：eCachePrefix <br>
	 * 描述：缓存前缀默认为tiku <br>
	 * 作者：赵增斌 E-mail:zhaozengbin@gmail.com QQ:4415599
	 * weibo:http://weibo.com/zhaozengbin <br>
	 * 日期： 2017年5月23日 下午3:24:32 <br>
	 *
	 * @return
	 */
	ECachePrefix eCachePrefix() default ECachePrefix.TI_KU;

}