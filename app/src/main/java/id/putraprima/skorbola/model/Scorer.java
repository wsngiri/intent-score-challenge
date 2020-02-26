package id.putraprima.skorbola.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Scorer implements Parcelable {

    private String nameScorer;
    private  int Scorer;

    public Scorer(String nameScorer, int scorer) {
        this.nameScorer = nameScorer;
        Scorer = scorer;
    }

    protected Scorer(Parcel in) {
        nameScorer = in.readString();
        Scorer = in.readInt();
    }

    public static final Creator<Scorer> CREATOR = new Creator<Scorer>() {
        @Override
        public Scorer createFromParcel(Parcel in) {
            return new Scorer(in);
        }

        @Override
        public Scorer[] newArray(int size) {
            return new Scorer[size];
        }
    };

    public String getNameScorer() {
        return nameScorer;
    }

    public void setNameScorer(String nameScorer) {
        this.nameScorer = nameScorer;
    }

    public int getScorer() {
        return Scorer;
    }

    public void setScorer(int scorer) {
        Scorer = scorer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameScorer);
        dest.writeInt(Scorer);
    }
}
