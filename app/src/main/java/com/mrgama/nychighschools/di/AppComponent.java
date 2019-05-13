package com.mrgama.nychighschools.di;

import android.content.Context;

import com.mrgama.nychighschools.MainActivity;
import com.mrgama.nychighschools.ui.schools.SchoolDetailFragment;
import com.mrgama.nychighschools.ui.schools.SchoolsListFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ContextModule.class, SchoolsModule.class})
public interface AppComponent {
	Context getContext();

	void inject(MainActivity mainActivity);
	void inject(SchoolDetailFragment schoolDetailFragment);
	void inject(SchoolsListFragment schoolsListFragment);
}
