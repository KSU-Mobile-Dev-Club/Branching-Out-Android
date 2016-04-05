package com.teioh08.branchingout.Maps;

import android.os.Bundle;


public interface LifeCycleMap {

    void init(Bundle bundle);

    void onSavedState(Bundle bundle);

    void onRestoreState(Bundle bundle);

    void onPause();

    void onResume();

    void onDestroy();
}
