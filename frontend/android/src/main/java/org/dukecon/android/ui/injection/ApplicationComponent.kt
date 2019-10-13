package org.dukecon.android.ui.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import org.dukecon.android.ui.app.DukeconApplication
import org.dukecon.android.ui.features.eventdetail.di.EventDetailComponent
import org.dukecon.android.ui.features.eventdetail.di.EventDetailModule
import org.dukecon.android.ui.features.info.InfoComponent
import org.dukecon.android.ui.features.info.InfoModule
import org.dukecon.android.ui.features.main.MainComponent
import org.dukecon.android.ui.features.main.MainModule
import org.dukecon.android.ui.features.speakerdetail.SpeakerDetailComponent
import org.dukecon.android.ui.features.timemachine.BuildTypeModule
import org.dukecon.android.ui.features.timemachine.SetCustomdateTimeReceiver
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        ApplicationModule::class,
        DataModule::class,
        AuthModule::class,
        ConferenceModule::class,
        BuildTypeModule::class
    )
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun mainComponent(mainModule: MainModule): MainComponent
    fun inject(dukeconApplication: DukeconApplication)
    fun eventDetailComponent(sessionDetailModule: EventDetailModule): EventDetailComponent
    fun speakerDetailComponent(): SpeakerDetailComponent
    fun infoComponent(infoModule: InfoModule): InfoComponent
    fun inject(dukeconApplication: SetCustomdateTimeReceiver)
}
