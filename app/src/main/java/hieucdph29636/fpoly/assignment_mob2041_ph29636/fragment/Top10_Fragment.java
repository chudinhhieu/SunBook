package hieucdph29636.fpoly.assignment_mob2041_ph29636.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hieucdph29636.fpoly.assignment_mob2041_ph29636.Adapter.TopSachAdapter;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DAO.PhieuMuonDAO;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.DTO.TopSach;
import hieucdph29636.fpoly.assignment_mob2041_ph29636.R;

public class Top10_Fragment extends Fragment {
    RecyclerView recyclerView;
    TopSachAdapter adapter;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<TopSach> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top10,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rcv_qlts);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getTop10();
        adapter = new TopSachAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }
}
