package com.sokolov.bessonovscards.view.cards.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.view.cards.model.ISerializableCard;
import com.sokolov.bessonovscards.view.cards.model.SerializableCard;

public class PlaceholderFragment extends Fragment {

    private static final String CARD = "CARD";

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(ICard card) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putSerializable(CARD, new SerializableCard(card));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cards, container, false);
        ISerializableCard card = (ISerializableCard) getArguments().getSerializable(CARD);

        TextView tvText =
                (TextView) rootView.findViewById(R.id.tv_text);
        TextView tvTranslate =
                (TextView) rootView.findViewById(R.id.tv_translate);

        tvText.setText(card.text());
        tvText
                .setOnClickListener(v -> {
                    tvText.setVisibility(View.GONE);
                    tvTranslate.setVisibility(View.VISIBLE);
                });
        tvTranslate.setText(card.translate());
        tvTranslate
                .setOnClickListener(v -> {
                    tvText.setVisibility(View.VISIBLE);
                    tvTranslate.setVisibility(View.GONE);
                });

        rootView
                .findViewById(R.id.btn_next_category)
                .setOnClickListener(v ->
                        ((OnCategoryChangeListener) getActivity())
                                .onNext(card));

        rootView
                .findViewById(R.id.btn_preview_category)
                .setOnClickListener(v ->
                        ((OnCategoryChangeListener) getActivity())
                                .onPreviews(card));

        rootView
                .findViewById(R.id.btn_edit_card)
                .setOnClickListener(v -> {
                    rootView.findViewById(R.id.rl_static_text).setVisibility(View.INVISIBLE);
                    rootView.findViewById(R.id.rl_editable_text).setVisibility(View.VISIBLE);
                    ((EditText) rootView.findViewById(R.id.et_text)).setText(card.text());
                    ((EditText) rootView.findViewById(R.id.et_translate)).setText(card.translate());
                });

        rootView
                .findViewById(R.id.btn_confirm_edit_card)
                .setOnClickListener(v -> {
                    rootView.findViewById(R.id.rl_static_text).setVisibility(View.VISIBLE);
                    rootView.findViewById(R.id.rl_editable_text).setVisibility(View.INVISIBLE);
                    ((OnCardEditListener) getActivity())
                            .onCardEdit(
                                    new Card(
                                            card.id(),
                                            ((EditText) rootView.findViewById(R.id.et_text)).getText().toString(),
                                            ((EditText) rootView.findViewById(R.id.et_translate)).getText().toString(),
                                            card.categoryName()));
                });

        rootView
                .findViewById(R.id.btn_pronounce_card)
                .setOnClickListener(v -> {
                    String selectedText =
                            card
                                    .text()
                                    .substring(
                                            tvText.getSelectionStart(),
                                            tvText.getSelectionEnd());

                    String textToPronounce;
                    if (selectedText.length() == 0) {
                        textToPronounce = card.text();
                    } else {
                        textToPronounce = selectedText;
                    }

                    ((OnTextPronounceListener) getActivity()).onTextPronounce(textToPronounce);
                });

        return rootView;
    }
}
