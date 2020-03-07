/*
 * Copyright (C) 2014 Balys Valentukevicius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.material.lib.widget;


import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.animation.*;


import java.lang.ref.WeakReference;



public class MaterialRippleLayoutCompat {
    AnimatorSet    rippleAnimator;
    ObjectAnimator hoverAnimator;
    private WeakReference<MaterialRippleLayout> host;

    Property<MaterialRippleLayout, Float> radiusProperty
    = new Property<MaterialRippleLayout, Float>(Float.class, "radius") {
        @Override
        public Float get(MaterialRippleLayout object) {
            return object.getRadius();
        }

        @Override
        public void set(MaterialRippleLayout object, Float value) {
            object.setRadius(value);
        }
    };

    Property<MaterialRippleLayout, Integer> circleAlphaProperty
    = new Property<MaterialRippleLayout, Integer>(Integer.class, "rippleAlpha") {
        @Override
        public Integer get(MaterialRippleLayout object) {
            return object.getRippleAlpha();
        }

        @Override
        public void set(MaterialRippleLayout object, Integer value) {
            object.setRippleAlpha(value);
        }
    };

    public MaterialRippleLayoutCompat(MaterialRippleLayout host) {
        this.host = new WeakReference<>(host);
    }


    void startHover() {
        if ( host.get() == null) return;

        if (hoverAnimator != null) {
            hoverAnimator.cancel();
        }
        final float radius = (float) (Math.sqrt(Math.pow(host.get().getWidth(), 2) + Math.pow(host.get().getHeight(), 2)) * 1.2f);
        hoverAnimator = ObjectAnimator.ofFloat(host.get(), radiusProperty, host.get().getRippleDiameter(), radius)
            .setDuration(MaterialRippleLayout.HOVER_DURATION);
        hoverAnimator.setInterpolator(new LinearInterpolator());
        hoverAnimator.start();
    }

    void startRipple(final Runnable animationEndRunnable) {
        if ( host.get() == null) return;

        float endRadius = host.get().getEndRadius();

        cancelAnimations();

        rippleAnimator = new AnimatorSet();
        rippleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                if (!host.get().getRipplePersistent()) {
                    host.get().setRadius(0);
                    host.get().setRippleAlpha(host.get().getRippleAlpha());
                }
                if (animationEndRunnable != null && host.get().getRippleDelayClick()) {
                    animationEndRunnable.run();
                }
                host.get().getChildView().setPressed(false);
            }
        });

        ObjectAnimator ripple = ObjectAnimator.ofFloat(host.get(), radiusProperty, host.get().getRadius(), endRadius);
        ripple.setDuration(host.get().getRippleDuration());
        ripple.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator fade = ObjectAnimator.ofInt(host.get(), circleAlphaProperty, host.get().getRippleAlpha(), 0);
        fade.setDuration(host.get().getRippleFadeDuration());
        fade.setInterpolator(new AccelerateInterpolator());
        fade.setStartDelay(host.get().getRippleDuration() - host.get().getRippleFadeDuration() - MaterialRippleLayout.FADE_EXTRA_DELAY);

        if (host.get().getRipplePersistent()) {
            rippleAnimator.play(ripple);
        } else if (host.get().getRadius() > endRadius) {
            fade.setStartDelay(0);
            rippleAnimator.play(fade);
        } else {
            rippleAnimator.playTogether(ripple, fade);
        }
        rippleAnimator.start();
    }

    void cancelAnimations() {
        if (rippleAnimator != null) {
            rippleAnimator.cancel();
            rippleAnimator.removeAllListeners();
        }

        if (hoverAnimator != null) {
            hoverAnimator.cancel();
        }
    }

    void cancelHoverAnimator(){
        if (hoverAnimator != null) {
            hoverAnimator.cancel();
        }
    }

}
