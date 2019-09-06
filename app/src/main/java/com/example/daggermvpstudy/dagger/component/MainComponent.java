package com.example.daggermvpstudy.dagger.component;

import com.example.daggermvpstudy.MainActivity;
import com.example.daggermvpstudy.dagger.model.MainModule;

import dagger.Component;
import dagger.Module;

/**
 * Component标记的类必须是抽象类或者接口、
 *
 * Component在目标类中所依赖的其他类的构造函数之间起到一个桥梁的作用
 *
 * 工作原理：Component需要引用到目标类的实例
 * Component会查找目标类中用Inject注解标注的属性
 * 查找到相应的属性后会接着查找该属性对应的用Inject标注的构造函数（这时候就产生联系类）
 * 剩下的工作是初始化该属性的实例并把实例进行赋值
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    public void inject(MainActivity mainActivity);
}
