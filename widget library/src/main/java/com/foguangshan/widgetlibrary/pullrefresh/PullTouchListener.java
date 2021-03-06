/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foguangshan.widgetlibrary.pullrefresh;

import android.view.MotionEvent;

import com.foguangshan.widgetlibrary.pullrefresh.PullGestureDetector.OnTouchListener;
import com.foguangshan.widgetlibrary.pullrefresh.PullViewBase.PullStatus;

/**
 * 触摸事件处理监听器
 */
@SuppressWarnings("rawtypes")
public class PullTouchListener implements OnTouchListener {
	private PullViewBase pullViewBase;
    
    public PullTouchListener(PullViewBase pullViewBase) {
		super();
		this.pullViewBase = pullViewBase;
	}

    @Override
    public boolean onTouchDown(MotionEvent e) {
        if(pullViewBase.getPullScroller().isScrolling()){
        	pullViewBase.getPullScroller().abortScroll();
        }
        pullViewBase.setInterceptTouchEvent(false);
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
	public boolean onTouchScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		switch(pullViewBase.getPullStatus()){
    		case PULL_HEADER : 
    			if(pullViewBase.isVerticalPull()){
    				pullViewBase.scrollBy(0, (int) (distanceY * pullViewBase.getElasticForce()));
    				if(pullViewBase.getScrollY() >= 0 && Math.abs(distanceY) <= 10){
    					pullViewBase.setPullStatus(PullStatus.NORMAL);
        			}
    			}else{
    				pullViewBase.scrollBy((int) (distanceX * pullViewBase.getElasticForce()), 0);
    				if(pullViewBase.getScrollX() >= 0 && Math.abs(distanceX) <= 10){
    					pullViewBase.setPullStatus(PullStatus.NORMAL);
    				}
    			}
    			if(pullViewBase.getPullHeaderView() != null){
    				pullViewBase.getPullHeaderView().onScroll(Math.abs(pullViewBase.isVerticalPull() ? pullViewBase.getScrollY() : pullViewBase.getScrollX()));
    	        }
    			pullViewBase.scrollPullViewToHeader(pullViewBase.getPullView());
    			break;
    		case PULL_FOOTER : 
    			if(pullViewBase.isVerticalPull()){
    				pullViewBase.scrollBy(0, (int) (distanceY * pullViewBase.getElasticForce()));
    				if(pullViewBase.getScrollY() <= 0 && Math.abs(distanceY) <= 10){
    					pullViewBase.setPullStatus(PullStatus.NORMAL);
    				}
    			}else{
    				pullViewBase.scrollBy((int) (distanceX * pullViewBase.getElasticForce()), 0);
    				if(pullViewBase.getScrollX() <= 0 && Math.abs(distanceX) <= 10){
    					pullViewBase.setPullStatus(PullStatus.NORMAL);
    				}
    			}
    			if(pullViewBase.getPullFooterView() != null){
    				pullViewBase.getPullFooterView().onScroll(Math.abs(pullViewBase.isVerticalPull() ? pullViewBase.getScrollY() : pullViewBase.getScrollX()));
    	        }
    			pullViewBase.scrollPullViewToFooter(pullViewBase.getPullView());
    			break;
    		default :
    			if(pullViewBase.isVerticalPull()){
    				if(distanceY < 0){	//如果向下拉
	        			if(pullViewBase.getScrollY() > 0){
	        				//如果Footer正在显示就先通过滚动隐藏Footer
	        				pullViewBase.logD("滚动：手动回滚Footer，ScrollY=" + pullViewBase.getScrollY());
    						pullViewBase.scrollBy(0, (int) (distanceY));
    						pullViewBase.scrollPullViewToFooter(pullViewBase.getPullView());
	        			}else{
	        				//如果可以拉伸Header并且
	        				if(pullViewBase.isCanPullHeader(pullViewBase.getPullView())){
		        				if(pullViewBase.getScrollY() <= (pullViewBase.getPullHeaderView() != null?pullViewBase.getPullHeaderView().getMinScrollValue():0)){
		        					pullViewBase.logD("滚动：开始拉伸头部，ScrollY=" + pullViewBase.getScrollY());
		        					pullViewBase.setPullStatus(PullStatus.PULL_HEADER);
		        				}
		        			}
	        			}
	        		}else if(distanceY > 0){	//如果向上拉
    					if(pullViewBase.getScrollY() < 0){
    						//如果Header正在显示就先通过滚动隐藏Header
    						pullViewBase.logD("滚动：手动回滚Header，ScrollY=" + pullViewBase.getScrollY());
    						pullViewBase.scrollBy(0, (int) (distanceY));
    						pullViewBase.scrollPullViewToHeader(pullViewBase.getPullView());
    					}else{
    						//如果可以拉伸Footer
    						if(pullViewBase.isCanPullFooter(pullViewBase.getPullView())){
    							pullViewBase.logD("滚动：开始拉伸尾部，ScrollY=" + pullViewBase.getScrollY());
    							pullViewBase.setPullStatus(PullStatus.PULL_FOOTER);
    						}
    					}
	        		}
    			}else{
    				if(distanceX < 0){	//如果向右拉
	        			if(pullViewBase.getScrollX() > 0){
	        				//如果Footer正在显示就先通过滚动隐藏Footer
	        				pullViewBase.logD("滚动：手动回滚Footer，ScrollX=" + pullViewBase.getScrollX());
    						pullViewBase.scrollBy((int) (distanceX), 0);
    						pullViewBase.scrollPullViewToFooter(pullViewBase.getPullView());
	        			}else{
	        				//如果可以拉伸Header并且
	        				if(pullViewBase.isCanPullHeader(pullViewBase.getPullView())){
		        				if(pullViewBase.getScrollX() <= (pullViewBase.getPullHeaderView() != null?pullViewBase.getPullHeaderView().getMinScrollValue():0)){
		        					pullViewBase.logD("滚动：开始拉伸头部，ScrollX=" + pullViewBase.getScrollX());
		        					pullViewBase.setPullStatus(PullStatus.PULL_HEADER);
		        				}
		        			}
	        			}
	        		}else if(distanceX > 0){	//如果向左拉
    					if(pullViewBase.getScrollX() < 0){
    						//如果Header正在显示就先通过滚动隐藏Header
    						pullViewBase.logD("滚动：手动回滚Header，ScrollX=" + pullViewBase.getScrollX());
    						pullViewBase.scrollBy((int) (distanceX), 0);
    						pullViewBase.scrollPullViewToHeader(pullViewBase.getPullView());
    					}else{
    						//如果可以拉伸Footer
    						if(pullViewBase.isCanPullFooter(pullViewBase.getPullView())){
    							pullViewBase.logD("滚动：开始拉伸尾部，ScrollX=" + pullViewBase.getScrollX());
    							pullViewBase.setPullStatus(PullStatus.PULL_FOOTER);
    						}
    					}
	        		}
    			}
    			break;
		}
    	return true;
	}
    
    @Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    	pullViewBase.setInterceptTouchEvent(pullViewBase.getPullStatus() != PullStatus.NORMAL);
		return true;
	}

    @Override
    public void onTouchUpOrCancel() {
        pullViewBase.getPullScroller().rollback();
    }
}
