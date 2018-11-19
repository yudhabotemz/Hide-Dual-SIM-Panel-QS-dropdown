package com.nyud.dualsimqspanelhider;

import android.view.View;
import android.view.ViewGroup;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;


public class DualSimQSpanelHider implements IXposedHookInitPackageResources {
		
		public static final String UI = "com.android.systemui";
		public static final String ID = "qs_multi_sim_preffered_slot";
		public static final String QS = "qs_panel_multi_sim_preffered_slot";

		@Override
		public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam Rpar) throws Throwable {
				if (!Rpar.packageName.equals(UI)) {
						return;
				}
				
				Rpar.res.hookLayout(UI, "layout", QS, new XC_LayoutInflated() {
						
						@Override
						public void handleLayoutInflated(LayoutInflatedParam LIpar) throws Throwable {
								View V = LIpar.view.findViewById(LIpar.res.getIdentifier(ID, "id", UI));
								ViewGroup.LayoutParams Lpar = V.getLayoutParams();
								Lpar.height = 0;
								V.setLayoutParams(Lpar);
						}
				});
		}
}
