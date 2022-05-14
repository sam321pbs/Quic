package com.sammengistu.quic.helpers

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions
import com.sammengistu.quic.testing.HiltTestActivity
import com.sammengistu.quic.ui.home.fragments.HomeFragment


/**
* launchFragmentInContainer from the androidx.fragment:fragment-testing library
* is NOT possible to use right now as it uses a hardcoded Activity under the hood
* (i.e. [EmptyFragmentActivity]) which is not annotated with @AndroidEntryPoint.
*
* As a workaround, use this function that is equivalent. It requires you to add
* [HiltTestActivity] in the debug folder and include it in the debug AndroidManifest.xml file
* as can be found in this project.
* https://github.com/android/architecture-samples/blob/dev-hilt/app/src/androidTest/java/com/example/android/architecture/blueprints/todoapp/HiltExt.kt
*/
fun <T: Fragment>launchFragmentInHiltContainer(
    fragment: T
) {
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    )

    ActivityScenario.launch<HiltTestActivity>(startActivityIntent).onActivity { activity ->
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()
    }
}