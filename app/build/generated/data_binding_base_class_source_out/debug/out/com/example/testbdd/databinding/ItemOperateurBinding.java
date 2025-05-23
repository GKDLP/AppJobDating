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

public final class ItemOperateurBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView nomOperateur;

  private ItemOperateurBinding(@NonNull CardView rootView, @NonNull TextView nomOperateur) {
    this.rootView = rootView;
    this.nomOperateur = nomOperateur;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemOperateurBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemOperateurBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_operateur, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemOperateurBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.nomOperateur;
      TextView nomOperateur = ViewBindings.findChildViewById(rootView, id);
      if (nomOperateur == null) {
        break missingId;
      }

      return new ItemOperateurBinding((CardView) rootView, nomOperateur);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
