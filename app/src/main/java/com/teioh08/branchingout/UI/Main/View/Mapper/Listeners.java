package com.teioh08.branchingout.UI.Main.View.Mapper;

import com.teioh08.branchingout.Tree;


public class Listeners {

    public interface TreeInfoListener {
        void changeActivityTitle(String title);

        void setPathToTree(Tree tree);

        void onBackPressed();
    }

    public interface VerifyDialogListener {
        void goToMap();
    }

    public interface TreeListener {
        void startTreeInfoFragment(Tree tree);
    }

}
