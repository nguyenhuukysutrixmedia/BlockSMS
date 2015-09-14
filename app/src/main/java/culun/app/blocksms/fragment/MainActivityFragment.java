package culun.app.blocksms.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import culun.app.blocksms.R;
import culun.app.blocksms.model.MySms;
import culun.app.blocksms.ormlite.DatabaseHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private TextView mTvSample;
    private DatabaseHelper databaseHelper = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initView(view);
        loadData();

        return view;
    }

    /**
     * @param v
     */
    private void initView(View v) {

        mTvSample = (TextView) v.findViewById(R.id.main_fragment_tv_sample);
    }

    /**
     *
     */
    private void loadData() {

        String string = "";

        RuntimeExceptionDao<MySms, String> mySmsDao = getHelper().getMySmsDao();

        List<MySms> listSms = mySmsDao.queryForAll();
        if (listSms != null) {
            for (MySms mySms : listSms) {
                string += mySms + "\n";
            }
        }
        mTvSample.setText(string);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }
}

