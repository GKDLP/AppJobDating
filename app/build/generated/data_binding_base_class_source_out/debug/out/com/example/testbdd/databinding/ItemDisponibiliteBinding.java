// Generated by view binder compiler. Do not edit!
package com.example.testbdd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.testbdd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemDisponibiliteBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView dateDisponibilite;

  @NonNull
  public final TextView heureDisponibilite;

  private ItemDisponibiliteBinding(@NonNull CardView rootView, @NonNull TextView dateDisponibilite,
      @NonNull TextView heureDisponibilite) {
    this.rootView = rootView;
    this.dateDisponibilite = dateDisponibilite;
    this.heureDisponibilite = heureDisponibilite;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemDisponibiliteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemDisponibiliteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_disponibilite, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemDisponibiliteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dateDisponibilite;
      TextView dateDisponibilite = ViewBindings.findChildViewById(rootView, id);
      if (dateDisponibilite == null) {
        break missingId;
      }

      id = R.id.heureDisponibilite;
      TextView heureDisponibilite = ViewBindings.findChildViewById(rootView, id);
      if (heureDisponibilite == null) {
        break missingId;
      }

      return new ItemDisponibiliteBinding((CardView) rootView, dateDisponibilite,
          heureDisponibilite);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
