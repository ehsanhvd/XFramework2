// "Therefore those skilled at the unorthodox
// are infinite as heaven and earth;
// inexhaustible as the great rivers.
// When they come to an end;
// they begin again;
// like the days and months;
// they die and are reborn;
// like the four seasons."
//
// - Sun Tsu;
// "The Art of War"

package com.theartofdev.edmodo.cropper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * All the possible options that can be set to customize crop image.<br>
 * Initialized with default values.
 */
public class CropImageOptions implements Parcelable {

    public static final Parcelable.Creator<CropImageOptions> CREATOR = new Parcelable.Creator<CropImageOptions>() {
        @Override
        public CropImageOptions createFromParcel(Parcel source) {
            return new CropImageOptions(source);
        }

        @Override
        public CropImageOptions[] newArray(int size) {
            return new CropImageOptions[size];
        }
    };
    /**
     * The shape of the cropping window.
     */
    public CropImageView.CropShape cropShape;
    /**
     * An edge of the crop window will snap to the corresponding edge of a specified bounding box when
     * the crop window edge is less than or equal to this distance (in pixels) away from the bounding
     * box edge. (in pixels)
     */
    public float snapRadius;
    /**
     * The radius of the touchable area around the handle. (in pixels)<br>
     * We are basing this value off of the recommended 48dp Rhythm.<br>
     * See: http://developer.android.com/design/style/metrics-grids.html#48dp-rhythm
     */
    public float touchRadius;
    /**
     * whether the guidelines should be on, off, or only showing when resizing.
     */
    public CropImageView.Guidelines guidelines;
    /**
     * The initial scale type of the image in the crop image view
     */
    public CropImageView.ScaleType scaleType;
    /**
     * if to show crop overlay UI what contains the crop window UI surrounded by background over the
     * cropping image.<br>
     * default: true, may disable for animation or frame transition.
     */
    public boolean showCropOverlay;
    /**
     * if to show progress bar when image async loading/cropping is in progress.<br>
     * default: true, disable to provide custom progress bar UI.
     */
    public boolean showProgressBar;
    /**
     * if auto-zoom functionality is enabled.<br>
     * default: true.
     */
    public boolean autoZoomEnabled;
    /**
     * if multi-touch should be enabled on the crop box default: false
     */
    public boolean multiTouchEnabled;
    /**
     * The max zoom allowed during cropping.
     */
    public int maxZoom;
    /**
     * The initial crop window padding from image borders in percentage of the cropping image
     * dimensions.
     */
    public float initialCropWindowPaddingRatio;
    /**
     * whether the width to height aspect ratio should be maintained or free to change.
     */
    public boolean fixAspectRatio;
    /**
     * the X value of the aspect ratio.
     */
    public int aspectRatioX;
    /**
     * the Y value of the aspect ratio.
     */
    public int aspectRatioY;
    /**
     * the thickness of the guidelines lines in pixels. (in pixels)
     */
    public float borderLineThickness;
    /**
     * the color of the guidelines lines
     */
    public int borderLineColor;
    /**
     * thickness of the corner line. (in pixels)
     */
    public float borderCornerThickness;
    /**
     * the offset of corner line from crop window border. (in pixels)
     */
    public float borderCornerOffset;
    /**
     * the length of the corner line away from the corner. (in pixels)
     */
    public float borderCornerLength;
    /**
     * the color of the corner line
     */
    public int borderCornerColor;
    /**
     * the thickness of the guidelines lines. (in pixels)
     */
    public float guidelinesThickness;
    /**
     * the color of the guidelines lines
     */
    public int guidelinesColor;
    /**
     * the color of the overlay background around the crop window cover the image parts not in the
     * crop window.
     */
    public int backgroundColor;
    /**
     * the min width the crop window is allowed to be. (in pixels)
     */
    public int minCropWindowWidth;
    /**
     * the min height the crop window is allowed to be. (in pixels)
     */
    public int minCropWindowHeight;
    /**
     * the min width the resulting cropping image is allowed to be, affects the cropping window
     * limits. (in pixels)
     */
    public int minCropResultWidth;
    /**
     * the min height the resulting cropping image is allowed to be, affects the cropping window
     * limits. (in pixels)
     */
    public int minCropResultHeight;
    /**
     * the max width the resulting cropping image is allowed to be, affects the cropping window
     * limits. (in pixels)
     */
    public int maxCropResultWidth;
    /**
     * the max height the resulting cropping image is allowed to be, affects the cropping window
     * limits. (in pixels)
     */
    public int maxCropResultHeight;
    /**
     * the title of the {@link CropImageActivity}
     */
    public CharSequence activityTitle;
    /**
     * the color to use for action bar items icons
     */
    public int activityMenuIconColor;
    /**
     * the Android Uri to save the cropped image to
     */
    public Uri outputUri;
    /**
     * the compression format to use when writing the image
     */
    public Bitmap.CompressFormat outputCompressFormat;
    /**
     * the quality (if applicable) to use when writing the image (0 - 100)
     */
    public int outputCompressQuality;
    /**
     * the width to resize the cropped image to (see options)
     */
    public int outputRequestWidth;
    /**
     * the height to resize the cropped image to (see options)
     */
    public int outputRequestHeight;
    /**
     * the resize method to use on the cropped bitmap (see options documentation)
     */
    public CropImageView.RequestSizeOptions outputRequestSizeOptions;
    /**
     * if the result of crop image activity should not save the cropped image bitmap
     */
    public boolean noOutputImage;
    /**
     * the initial rectangle to set on the cropping image after loading
     */
    public Rect initialCropWindowRectangle;
    /**
     * the initial rotation to set on the cropping image after loading (0-360 degrees clockwise)
     */
    public int initialRotation;
    /**
     * if to allow (all) rotation during cropping (activity)
     */
    public boolean allowRotation;
    /**
     * if to allow (all) flipping during cropping (activity)
     */
    public boolean allowFlipping;
    /**
     * if to allow counter-clockwise rotation during cropping (activity)
     */
    public boolean allowCounterRotation;
    /**
     * the amount of degrees to rotate clockwise or counter-clockwise
     */
    public int rotationDegrees;
    /**
     * whether the image should be flipped horizontally
     */
    public boolean flipHorizontally;
    /**
     * whether the image should be flipped vertically
     */
    public boolean flipVertically;
    /**
     * optional, the text of the crop menu crop button
     */
    public CharSequence cropMenuCropButtonTitle;
    /**
     * optional image resource to be used for crop menu crop icon instead of text
     */
    public int cropMenuCropButtonIcon;
    public boolean useCamera = true;
    public boolean useGallery = true;

    /**
     * Init options with defaults.
     */
    public CropImageOptions() {

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();

        cropShape = CropImageView.CropShape.RECTANGLE;
        snapRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, dm);
        touchRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, dm);
        guidelines = CropImageView.Guidelines.ON_TOUCH;
        scaleType = CropImageView.ScaleType.FIT_CENTER;
        showCropOverlay = true;
        showProgressBar = true;
        autoZoomEnabled = true;
        multiTouchEnabled = false;
        maxZoom = 4;
        initialCropWindowPaddingRatio = 0.1f;

        fixAspectRatio = false;
        aspectRatioX = 1;
        aspectRatioY = 1;

        borderLineThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, dm);
        borderLineColor = Color.argb(170, 255, 255, 255);
        borderCornerThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, dm);
        borderCornerOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, dm);
        borderCornerLength = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, dm);
        borderCornerColor = Color.WHITE;

        guidelinesThickness = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm);
        guidelinesColor = Color.argb(170, 255, 255, 255);
        backgroundColor = Color.argb(119, 0, 0, 0);

        minCropWindowWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, dm);
        minCropWindowHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, dm);
        minCropResultWidth = 40;
        minCropResultHeight = 40;
        maxCropResultWidth = 99999;
        maxCropResultHeight = 99999;

        activityTitle = "";
        activityMenuIconColor = 0;

        outputUri = Uri.EMPTY;
        outputCompressFormat = Bitmap.CompressFormat.JPEG;
        outputCompressQuality = 90;
        outputRequestWidth = 0;
        outputRequestHeight = 0;
        outputRequestSizeOptions = CropImageView.RequestSizeOptions.NONE;
        noOutputImage = false;

        initialCropWindowRectangle = null;
        initialRotation = -1;
        allowRotation = true;
        allowFlipping = true;
        allowCounterRotation = false;
        rotationDegrees = 90;
        flipHorizontally = false;
        flipVertically = false;
        cropMenuCropButtonTitle = null;

        cropMenuCropButtonIcon = 0;
    }

    protected CropImageOptions(Parcel in) {
        int tmpCropShape = in.readInt();
        this.cropShape = tmpCropShape == -1 ? null : CropImageView.CropShape.values()[tmpCropShape];
        this.snapRadius = in.readFloat();
        this.touchRadius = in.readFloat();
        int tmpGuidelines = in.readInt();
        this.guidelines = tmpGuidelines == -1 ? null : CropImageView.Guidelines.values()[tmpGuidelines];
        int tmpScaleType = in.readInt();
        this.scaleType = tmpScaleType == -1 ? null : CropImageView.ScaleType.values()[tmpScaleType];
        this.showCropOverlay = in.readByte() != 0;
        this.showProgressBar = in.readByte() != 0;
        this.autoZoomEnabled = in.readByte() != 0;
        this.multiTouchEnabled = in.readByte() != 0;
        this.maxZoom = in.readInt();
        this.initialCropWindowPaddingRatio = in.readFloat();
        this.fixAspectRatio = in.readByte() != 0;
        this.aspectRatioX = in.readInt();
        this.aspectRatioY = in.readInt();
        this.borderLineThickness = in.readFloat();
        this.borderLineColor = in.readInt();
        this.borderCornerThickness = in.readFloat();
        this.borderCornerOffset = in.readFloat();
        this.borderCornerLength = in.readFloat();
        this.borderCornerColor = in.readInt();
        this.guidelinesThickness = in.readFloat();
        this.guidelinesColor = in.readInt();
        this.backgroundColor = in.readInt();
        this.minCropWindowWidth = in.readInt();
        this.minCropWindowHeight = in.readInt();
        this.minCropResultWidth = in.readInt();
        this.minCropResultHeight = in.readInt();
        this.maxCropResultWidth = in.readInt();
        this.maxCropResultHeight = in.readInt();
        activityTitle = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.activityMenuIconColor = in.readInt();
        this.outputUri = in.readParcelable(Uri.class.getClassLoader());
        int tmpOutputCompressFormat = in.readInt();
        this.outputCompressFormat = tmpOutputCompressFormat == -1 ? null : Bitmap.CompressFormat.values()[tmpOutputCompressFormat];
        this.outputCompressQuality = in.readInt();
        this.outputRequestWidth = in.readInt();
        this.outputRequestHeight = in.readInt();
        int tmpOutputRequestSizeOptions = in.readInt();
        this.outputRequestSizeOptions = tmpOutputRequestSizeOptions == -1 ? null : CropImageView.RequestSizeOptions.values()[tmpOutputRequestSizeOptions];
        this.noOutputImage = in.readByte() != 0;
        this.initialCropWindowRectangle = in.readParcelable(Rect.class.getClassLoader());
        this.initialRotation = in.readInt();
        this.allowRotation = in.readByte() != 0;
        this.allowFlipping = in.readByte() != 0;
        this.allowCounterRotation = in.readByte() != 0;
        this.rotationDegrees = in.readInt();
        this.flipHorizontally = in.readByte() != 0;
        this.flipVertically = in.readByte() != 0;
        cropMenuCropButtonTitle = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.cropMenuCropButtonIcon = in.readInt();
        this.useCamera = in.readByte() != 0;
        this.useGallery = in.readByte() != 0;
    }

    /**
     * Validate all the options are withing valid range.
     *
     * @throws IllegalArgumentException if any of the options is not valid
     */
    public void validate() {
        if (maxZoom < 0) {
            throw new IllegalArgumentException("Cannot set max zoom to a number < 1");
        }
        if (touchRadius < 0) {
            throw new IllegalArgumentException("Cannot set touch radius value to a number <= 0 ");
        }
        if (initialCropWindowPaddingRatio < 0 || initialCropWindowPaddingRatio >= 0.5) {
            throw new IllegalArgumentException(
                    "Cannot set initial crop window padding value to a number < 0 or >= 0.5");
        }
        if (aspectRatioX <= 0) {
            throw new IllegalArgumentException(
                    "Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        if (aspectRatioY <= 0) {
            throw new IllegalArgumentException(
                    "Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        if (borderLineThickness < 0) {
            throw new IllegalArgumentException(
                    "Cannot set line thickness value to a number less than 0.");
        }
        if (borderCornerThickness < 0) {
            throw new IllegalArgumentException(
                    "Cannot set corner thickness value to a number less than 0.");
        }
        if (guidelinesThickness < 0) {
            throw new IllegalArgumentException(
                    "Cannot set guidelines thickness value to a number less than 0.");
        }
        if (minCropWindowHeight < 0) {
            throw new IllegalArgumentException(
                    "Cannot set min crop window height value to a number < 0 ");
        }
        if (minCropResultWidth < 0) {
            throw new IllegalArgumentException("Cannot set min crop result width value to a number < 0 ");
        }
        if (minCropResultHeight < 0) {
            throw new IllegalArgumentException(
                    "Cannot set min crop result height value to a number < 0 ");
        }
        if (maxCropResultWidth < minCropResultWidth) {
            throw new IllegalArgumentException(
                    "Cannot set max crop result width to smaller value than min crop result width");
        }
        if (maxCropResultHeight < minCropResultHeight) {
            throw new IllegalArgumentException(
                    "Cannot set max crop result height to smaller value than min crop result height");
        }
        if (outputRequestWidth < 0) {
            throw new IllegalArgumentException("Cannot set request width value to a number < 0 ");
        }
        if (outputRequestHeight < 0) {
            throw new IllegalArgumentException("Cannot set request height value to a number < 0 ");
        }
        if (rotationDegrees < 0 || rotationDegrees > 360) {
            throw new IllegalArgumentException(
                    "Cannot set rotation degrees value to a number < 0 or > 360");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cropShape == null ? -1 : this.cropShape.ordinal());
        dest.writeFloat(this.snapRadius);
        dest.writeFloat(this.touchRadius);
        dest.writeInt(this.guidelines == null ? -1 : this.guidelines.ordinal());
        dest.writeInt(this.scaleType == null ? -1 : this.scaleType.ordinal());
        dest.writeByte(this.showCropOverlay ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showProgressBar ? (byte) 1 : (byte) 0);
        dest.writeByte(this.autoZoomEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(this.multiTouchEnabled ? (byte) 1 : (byte) 0);
        dest.writeInt(this.maxZoom);
        dest.writeFloat(this.initialCropWindowPaddingRatio);
        dest.writeByte(this.fixAspectRatio ? (byte) 1 : (byte) 0);
        dest.writeInt(this.aspectRatioX);
        dest.writeInt(this.aspectRatioY);
        dest.writeFloat(this.borderLineThickness);
        dest.writeInt(this.borderLineColor);
        dest.writeFloat(this.borderCornerThickness);
        dest.writeFloat(this.borderCornerOffset);
        dest.writeFloat(this.borderCornerLength);
        dest.writeInt(this.borderCornerColor);
        dest.writeFloat(this.guidelinesThickness);
        dest.writeInt(this.guidelinesColor);
        dest.writeInt(this.backgroundColor);
        dest.writeInt(this.minCropWindowWidth);
        dest.writeInt(this.minCropWindowHeight);
        dest.writeInt(this.minCropResultWidth);
        dest.writeInt(this.minCropResultHeight);
        dest.writeInt(this.maxCropResultWidth);
        dest.writeInt(this.maxCropResultHeight);
        TextUtils.writeToParcel(activityTitle, dest, flags);
        dest.writeInt(this.activityMenuIconColor);
        dest.writeParcelable(this.outputUri, flags);
        dest.writeInt(this.outputCompressFormat == null ? -1 : this.outputCompressFormat.ordinal());
        dest.writeInt(this.outputCompressQuality);
        dest.writeInt(this.outputRequestWidth);
        dest.writeInt(this.outputRequestHeight);
        dest.writeInt(this.outputRequestSizeOptions == null ? -1 : this.outputRequestSizeOptions.ordinal());
        dest.writeByte(this.noOutputImage ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.initialCropWindowRectangle, flags);
        dest.writeInt(this.initialRotation);
        dest.writeByte(this.allowRotation ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allowFlipping ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allowCounterRotation ? (byte) 1 : (byte) 0);
        dest.writeInt(this.rotationDegrees);
        dest.writeByte(this.flipHorizontally ? (byte) 1 : (byte) 0);
        dest.writeByte(this.flipVertically ? (byte) 1 : (byte) 0);
        TextUtils.writeToParcel(cropMenuCropButtonTitle, dest, flags);
        dest.writeInt(this.cropMenuCropButtonIcon);
        dest.writeByte(this.useCamera ? (byte) 1 : (byte) 0);
        dest.writeByte(this.useGallery ? (byte) 1 : (byte) 0);
    }
}
