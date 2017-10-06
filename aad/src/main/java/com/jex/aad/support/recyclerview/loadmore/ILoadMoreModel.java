package com.jex.aad.support.recyclerview.loadmore;

import com.jex.aad.base.BaseModel;
import com.jex.aad.http.IPageModel;

import java.util.Map;

import io.reactivex.Observable;


/**
 * LoadMore M层
 * Created by lizhihua on 2016/12/14.
 */

public interface ILoadMoreModel<B> extends BaseModel {
    Observable<? extends IPageModel<B>> loadData(Map<String, Object> params, int pageIndex);
}
