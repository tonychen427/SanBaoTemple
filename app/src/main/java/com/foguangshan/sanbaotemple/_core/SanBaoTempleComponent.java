package com.foguangshan.sanbaotemple._core;

import com.foguangshan.sanbaotemple.MainActivity;
import com.foguangshan.sanbaotemple.MyPageActivity;
import com.foguangshan.sanbaotemple.adapter.BannerAdapter;
import com.foguangshan.sanbaotemple.adapter.BookChapterAdapter;
import com.foguangshan.sanbaotemple.adapter.BookGridAdapter;
import com.foguangshan.sanbaotemple.adapter.BookListAdapter;
import com.foguangshan.sanbaotemple.asyncTask.GetDemoJson;

import dagger.Component;

/**
 * Created by MacBookPro on 2/27/15.
 */

@ActivityScope
@Component(modules = SanBaoTempleModule.class)
public interface SanBaoTempleComponent {
        void initializeMainActivity(MainActivity mainActivity);
        void initializeMyPageActivity(MyPageActivity myPageActivity);
        void initializeGetDemoJson(GetDemoJson GetDemoJson);

        void initializeBannerListAdapter(BannerAdapter bannerAdapter);
        void initializeBookListAdapter(BookListAdapter bookListAdapter);
        void initializeBookGridAdapter(BookGridAdapter bookGridAdapter);
        void initializeBookChapterAdapter(BookChapterAdapter bookDetailAdapter);
}
