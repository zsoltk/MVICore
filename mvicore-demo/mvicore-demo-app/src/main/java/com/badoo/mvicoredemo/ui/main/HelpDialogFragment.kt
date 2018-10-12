package com.badoo.mvicoredemo.ui.main

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.Html

class HelpDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(activity!!)
            .setMessage(
                Html.fromHtml(
                    "<h2>UI controls</h2>" +
                        "<ul>" +
                        "<li>Tap the colored boxes to set selection.</li>" +
                        "<li>Tap the FAB icon to increase counter. </li>" +
                        "<li>Tap the grey box / cat image to load another image (async). </li>" +
                        "<li>Tap TOASTS to show/hide fake analytics toast messages for UI interactions. This is to demonstrate time travel replay channels (more on that below)</li>" +
                        "<li>Tap SIGN OUT and SIGN IN again to see how destroying the scope resets the state. </li>" +
                        "</ul>" +

                        "<h2>Errors</h2>" +
                        "<ul>" +
                        "<li>The 'Simulated error was triggered' toast happens randomly when loading the image to demonstrate event handling. Check <b>Feature2</b> for that.</li>" +
                        "</ul>" +

                        "<h2>Time travel how-to</h2>" +
                        "<ul>" +
                        "<li>Slide with your finger from the right edge of the screen to open DebugDrawer (close this dialog first). </li>" +
                        "<li>You can record interaction with the UI then replay it. Don't forget to stop recording first :) </li>" +
                        "</ul>" +

                        "<h2>Time travel channels</h2>" +
                        "<ul>" +
                        "<li>You can select replay channel in the dropdown. To replay everything on the UI, select <b>MainActivity.ViewModels</b> channel. </li>" +
                        "<li>Notice how the 'Simulated error was triggered' error message doesn't replay on the <b>MainActivity.ViewModels</b> channel (it's not part of the state), but you can replay it on the <b>MainActivity.News</b> channel</li>" +
                        "<li>You can select to replay only <b>Feature1</b> (counter and colored boxes) or only <b>Feature2</b> (async image loading) internal channels and see what happens on the UI.</li>" +
                        "<li>Tap TOASTS to turn on showing fake analytics toast messages for UI interactions. See how they are replayed on the <b>MainActivity.Analytics</b> channel. </li>" +
                        "</ul>" +

                        "<h2>Points of interest in the source code</h2>" +
                        "<ul>" +
                        "<li><b>MainActivityBindings</b> for reactive bindings</li>" +
                        "<li><b>Feature1</b> for basic example</li>" +
                        "<li><b>Feature2</b> for async loading and event handling ('Simulated error was triggered' toast is an event and not part of the state)</li>" +
                        "</ul>" +

                        "<h2>Retaining state</h2>" +
                        "<li>Rotate the device to see that the state is persisted (sorry for ugly landscape layout). </li>" +
                        "<li>Tap SIGN OUT and SIGN IN again to see how destroying the scope resets the state. </li>" +
                        "</ul>" +
                        ""))
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .create()
}
