package com.example.daggermvpstudy.dagger.model;

import com.example.daggermvpstudy.view.ILoginView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    ILoginView view;

    public MainModule(ILoginView view) {
        this.view = view;
    }

    @Provides
    ILoginView provideILoginView() {
        return view;
    }
}
