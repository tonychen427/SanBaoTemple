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

package com.foguangshan.widgetlibrary.pullrefresh.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

import com.foguangshan.widgetlibrary.pullrefresh.PullAdapterViewBase;

/**
 * 可拉的ExpandableListView
 */
public class PullExpandableListView extends PullAdapterViewBase<ExpandableListView> {
    public PullExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullExpandableListView(Context context) {
        super(context);
    }

    @Override
    public ExpandableListView createPullView() {
        ExpandableListView listView = new ExpandableListView(getContext());
        listView.setId(android.R.id.list);
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setBackgroundColor(Color.WHITE);
        return listView;
    }
}
