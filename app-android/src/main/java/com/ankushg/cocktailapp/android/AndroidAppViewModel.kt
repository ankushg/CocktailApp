package com.ankushg.cocktailapp.android

import androidx.lifecycle.ViewModel
import com.ankushg.cocktailapp.shared.app.AppViewModel

class AndroidAppViewModel(appViewModel: AppViewModel) :
    ViewModel(),
    AppViewModel by appViewModel