package c0defather.examplemvp.injection.module

import c0defather.examplemvp.data.DataManager
import c0defather.examplemvp.data.DataManagerImpl
import dagger.Binds
import dagger.Module

/**
 * Created by kuanysh on 4/28/18.
 */
@Module
abstract class Bindings {

    @Binds
    internal abstract fun bindDataManger(manager: DataManagerImpl): DataManager

}