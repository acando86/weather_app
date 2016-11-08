package com.weatherapp.weatherapp.presentation.base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Test the basic mechanism that binds a view to the presenter
 * Created by alessandro.candolini on 08/11/2016.
 */

public class BasePresenterTest {

    @Mock
    private TestView mockTestView; // we don't call the constructore of this class

    @Spy
    private TestSimplePresenter testSimplePresenter = new TestSimplePresenter();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    public class TestView implements MvpView {

        public void onTestViewAttached() {}

        public void onTestViewDetatched() {}

        public void showLoading() {}

    }

    public class TestSimplePresenter extends BasePresenter<TestView> {}


    @Test
    public void testSimplePresenterNotAttached() {
        assertFalse(testSimplePresenter.isViewAttached());
        assertNull(testSimplePresenter.getMvpView());
    }

    @Test
    public void testSimplePresenterAttached() {
        testSimplePresenter.attachView(mockTestView);
        assertTrue(testSimplePresenter.isViewAttached());
        assertTrue(testSimplePresenter.getMvpView() == mockTestView);
    }

    @Test
    public void testSimplePresenterDetached() {
        testSimplePresenter.detachView();
        assertFalse(testSimplePresenter.isViewAttached());
        assertNull(testSimplePresenter.getMvpView());
    }

}
