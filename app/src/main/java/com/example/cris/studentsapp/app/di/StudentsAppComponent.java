package com.example.cris.studentsapp.app.di;

import com.example.cris.studentsapp.app.StudentsApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        StudentsAppModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class
})
public interface StudentsAppComponent {

    void inject(StudentsApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(StudentsApplication application);

        StudentsAppComponent build();
    }
}
