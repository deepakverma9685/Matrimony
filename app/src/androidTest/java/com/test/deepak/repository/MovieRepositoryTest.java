package com.test.deepak.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.test.deepak.data.Resource;
import com.test.deepak.data.local.dao.MovieDao;
import com.test.deepak.data.local.entity.MovieEntity;
import com.test.deepak.util.MockTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import static com.test.deepak.AppConstants.MOVIES_POPULAR;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryTest {

    @Mock
    MovieDao movieDao;

    @Mock
    MovieApiService movieApiService;

    private MovieRepository repository;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        repository = new MovieRepository(movieDao, movieApiService);
    }


    @Test
    public void loadMoviesList() {

        Flowable<List<MovieEntity>> loadFromDB = Flowable.empty();
        when(movieDao.getMoviesByType(MOVIES_POPULAR))
                .thenReturn(loadFromDB);

        MovieApiResponse mockResponse = MockTestUtil.mockMovieApiResponse(MOVIES_POPULAR);
        when(movieApiService.fetchMoviesByType(MOVIES_POPULAR, 1l))
                .thenReturn(Observable.just(mockResponse));

        Observable<Resource<List<MovieEntity>>>
                data = repository.loadMoviesByType(MOVIES_POPULAR);
        verify(movieDao).getMoviesByType(MOVIES_POPULAR);
        verify(movieApiService).fetchMoviesByType(MOVIES_POPULAR, 1l);

        TestObserver testObserver = new TestObserver();
        data.subscribe(testObserver);
        testObserver.assertNoErrors();
    }
}
