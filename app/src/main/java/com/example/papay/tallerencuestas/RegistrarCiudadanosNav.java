package com.example.papay.tallerencuestas;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RegistrarCiudadanosNav extends Fragment {
View view;
    private AppBarLayout appBar;

    private TabLayout tabs;

    private ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_registrar_ciudadanos_nav, container, false);


        View contenedor = (View) container.getParent();

        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);

        configView(view);

        return  view;
    }

    public void configView(View view){

        tabs = new TabLayout(getActivity());

        tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#000000"));

        appBar.addView(tabs);

        viewPager = (ViewPager) view.findViewById(R.id.pager);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        tabs.setupWithViewPager(viewPager);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabs);
    }
    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        String[] tituloTabs = {"Informacion Basica", "Nucleo Familiar", "Informacion Laboral"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new InformacionBasicaTab();
                case 1:
                    return new NucleoFamiliarTab();
                case 2:
                    return new InformacionLaboralTab();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tituloTabs[position];
        }
    }
}
