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

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;

/**
 * 可拉的AdapterView
 */
public abstract class PullAdapterViewBase<T extends AbsListView> extends PullViewBase<T>{
    public PullAdapterViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullAdapterViewBase(Context context) {
        super(context);
    }


    @Override
    public boolean isVerticalPull() {
        return true;
    }

    @Override
    public boolean isCanPullHeader(AbsListView pullView) {
        boolean result = false;
        final Adapter adapter = pullView.getAdapter();
        if (null != adapter && !adapter.isEmpty() && pullView.getFirstVisiblePosition() <= 1) {
            final View firstVisibleChild = pullView.getChildAt(0);
            if (firstVisibleChild != null) {
                result = firstVisibleChild.getTop() >= pullView.getTop();
            }
        }
        return result;
    }

    @Override
    public boolean isCanPullFooter(AbsListView pullView) {
        boolean result = false;
        final Adapter adapter = pullView.getAdapter();
        if (null != adapter && !adapter.isEmpty()) {
            final int lastItemPosition = pullView.getCount() - 1;
            final int lastVisiblePosition = pullView.getLastVisiblePosition();
//            Log.d(PullViewBase.class.getSimpleName(), "isLastItemVisible. Last Item Position: " + lastItemPosition + " Last Visible Pos: " + lastVisiblePosition);

            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - pullView.getFirstVisiblePosition();
                final View lastVisibleChild = pullView.getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    result = lastVisibleChild.getBottom() <= pullView.getBottom();
                }
            }
        }

        return result;
    }

    @Override
    public void scrollPullViewToHeader(AbsListView pullView) {
        pullView.setSelection(0);
    }

    @Override
    public void scrollPullViewToFooter(AbsListView pullView) {
        if(!pullView.getAdapter().isEmpty()){
            pullView.setSelection(pullView.getCount() - 1);
        }
    }
}
