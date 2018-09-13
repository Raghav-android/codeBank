package com.myezassets.utils.others;

import android.content.Context;
import android.content.SharedPreferences;

import com.myezassets.data.response.loginResponse.LoginBody;
import com.myezassets.data.response.registerResponse.UserModel;

/**
 * Created by NS on 4/5/2017.
 */
public class SharedPrefUtil {

    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String EMAIL = "email";
    public static final String IMAGE = "profileImage";
    public static final String USER_ID = "userId";
    public static final String DEVICE_TOKEN = "deviceToken";
    public static final String AUTH_TOKEN = "authToken";
    public static final String LOGIN = "login";


    /**
     * Name of the preference file
     */
    private static final String APP_PREFS = "application_preferences";
    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static SharedPrefUtil instance;

    public SharedPrefUtil(Context mContext) {
        this.mContext = mContext;
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SharedPrefUtil(context);
        }
    }

    public static SharedPrefUtil getInstance() {
        if (instance == null) {
            instance = new SharedPrefUtil(App.getInstance());
        }
        return instance;
    }


    /**
     * Save a string into shared preference
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     */
    public void saveString(String key, String value) {

        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    /**
     * Save a int into shared preference
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     */
    public void saveInt(String key, int value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    /**
     * Save a boolean into shared preference
     *
     * @param key   The name of the preference to modify
     * @param value The new value for the preference
     */
    public void saveBoolean(String key, boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or null.
     * Throws ClassCastException if there is a preference with this name that is not a String.
     */
    public String getString(String key) {
        //    mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * Throws ClassCastException if there is a preference with this name that is not a String.
     */
    public String getString(String key, String defaultValue) {
        //    mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0.
     * Throws ClassCastException if there is a preference with this name that is not a int.
     */
    public int getInt(String key) {
        // mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, 0);
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * Throws ClassCastException if there is a preference with this name that is not a int.
     */
    public int getInt(String key, int defaultValue) {
        //     mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or false.
     * Throws ClassCastException if there is a preference with this name that is not a boolean.
     */
    public boolean getBoolean(String key) {
        //     mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, false);
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * Throws ClassCastException if there is a preference with this name that is not a boolean.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, defaultValue);
    }


    /**
     * save the device token.
     *
     * @param token Vslue retrieved from the firebase.
     */
    public void saveDeviceToken(String token) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(DEVICE_TOKEN, token);
        mEditor.apply();
    }


    /**
     * get the current device token
     *
     * @return Returns the last update device token got from firebase.
     */
    public String getDeviceToken() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(DEVICE_TOKEN, "1234567890");
    }
//

    /**
     * save the userId of current user using the application
     *
     * @param userId this is the userId of the user
     */
    public void saveUserId(String userId) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_ID, userId);
        mEditor.apply();
    }

    /**
     * get current login user's userId .
     *
     * @return Returns the last userId saved.
     */
    public String getUserId() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(USER_ID, "");
    }

    /**
     * save the latest authorization token of the user
     *
     * @param authToken authoriztion token of the user.
     */
    public void saveAuthToken(String authToken) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(AUTH_TOKEN, authToken);
        mEditor.apply();
    }

    /**
     * get the authorization of the user
     */
    public String getAuthToken() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(AUTH_TOKEN, "");
    }


    /**
     * set login state oin the device is there any user current login device
     */
    public void setLogin(boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(LOGIN, value);
        mEditor.apply();
    }

    /**
     * @return is anyuser current login into the device accordingly true/false.
     */
    public boolean isLogin() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(LOGIN, false);
    }


    /**
     * save name of the user
     */
    public void saveName(String name) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(NAME, name);
        mEditor.apply();
    }

    /**
     * get name of the user
     */
    public String getName() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(NAME, "");
    }


    /**
     * save email of the user
     */
    public void saveEmail(String email) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(EMAIL, email);
        mEditor.apply();
    }

    /**
     * get email of the user
     */
    public String getEmail() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(EMAIL, "");
    }

    /**
     * save phoneNumber of the user
     */
    public void savePhoneNumber(String phoneNumber) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(PHONE_NUMBER, phoneNumber);
        mEditor.apply();
    }

    /**
     * get phoneNumber of the user
     */
    public String getPhoneNumber() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(PHONE_NUMBER, "");
    }

    /**
     * save image of the user
     */
    public void saveImage(String image) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(IMAGE, image);
        mEditor.apply();
    }

    /**
     * get image of the user
     */
    public String getImage() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(IMAGE, "");
    }

    /**
     * Clears the shared preference file
     */
    public void clear() {
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
        mSharedPreferences.edit().clear().apply();
    }


    /**
     * save user all data when user user login into the device
     *
     * @param userModel
     */
    public void saveLoginData(UserModel userModel) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_ID, String.valueOf(userModel.getId()));
        mEditor.putString(AUTH_TOKEN, userModel.getAuthorizationKey());
        mEditor.putString(NAME, userModel.getUsername());
        mEditor.putString(EMAIL, userModel.getEmail());
        mEditor.putString(IMAGE, userModel.getImage());
//        mEditor.putString(PHONE_NUMBER, userModel.getPhoneNumber());
        mEditor.apply();
    }

    /**
     * clear all user's save data whenever user logout the device.
     */
    public void onLogout() {
        mEditor = mSharedPreferences.edit();
        mEditor.remove(USER_ID);
        mEditor.remove(AUTH_TOKEN);
        mEditor.remove(NAME);
        mEditor.remove(EMAIL);
        mEditor.remove(IMAGE);
        mEditor.remove(PHONE_NUMBER);
        setLogin(false);
        mEditor.apply();
    }
}
