package com.test.deepak.viewmodel;


import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.test.InstrumentationRegistry;

import com.test.deepak.data.Resource;
import com.test.deepak.data.local.entity.TvEntity;
import com.test.deepak.ui.search.viewmodel.TvSearchViewModel;
import com.test.deepak.util.MockTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.test.deepak.AppConstants.MOVIES_POPULAR;

@RunWith(MockitoJUnitRunner.class)
public class TvSearchViewModelTest {


    private TvSearchViewModel tvSearchViewModel;

    @Mock
    Observer<Resource<List<TvEntity>>> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        Application app =
                (Application) InstrumentationRegistry
                        .getTargetContext()
                        .getApplicationContext();
        tvSearchViewModel = new TvSearchViewModel(app);
    }

    @Test
    public void searchTvEntities() {

        tvSearchViewModel.getTvsLiveData().observeForever(observer);
        tvSearchViewModel.searchTv("friends");

        assert(tvSearchViewModel.getTvsLiveData().getValue().isLoading());
        assert(tvSearchViewModel.getTvsLiveData().getValue().data == MockTestUtil.mockTvList(MOVIES_POPULAR));
    }
}
