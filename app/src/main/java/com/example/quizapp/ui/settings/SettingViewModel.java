package com.example.quizapp.ui.settings;

import androidx.lifecycle.ViewModel;
import com.example.quizapp.App;
import com.example.quizapp.utils.ShowToast;

public class SettingViewModel extends ViewModel {

    public void deleteHistory() {
        ShowToast.message("History cleared");
        App.historyStorage.deleteAll();
    }
}
