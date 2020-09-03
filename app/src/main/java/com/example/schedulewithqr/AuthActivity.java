package com.example.schedulewithqr;

/**
 * Created by tanchuev on 08.11.2017.
 */

public class AuthActivity extends SingleFragmentActivity {

    @Override
    protected AuthFragment getFragment() {
        return AuthFragment.newInstance();
    }
}
