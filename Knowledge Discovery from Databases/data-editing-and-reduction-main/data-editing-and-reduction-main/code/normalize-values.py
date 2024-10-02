import pandas as pd
import numpy as np
from sklearn.preprocessing import MinMaxScaler

def normalize_values(input_file, output_file):
    # Read the CSV file using pandas
    df = pd.read_csv(input_file)

    # Extract numerical attributes (excluding the last column) and class labels
    numerical_data = df.iloc[:, :-1].values
    class_labels = df.iloc[:, -1].values

    # Normalize the numerical data using MinMaxScaler
    scaler = MinMaxScaler()
    normalized_data = scaler.fit_transform(numerical_data)

    # Combine normalized numerical data and class labels
    df_normalized = pd.DataFrame(data=np.column_stack((normalized_data, class_labels)),
                                  columns=df.columns)

    # Write the normalized data to a new CSV file
    df_normalized.to_csv(output_file, index=False)

if __name__ == "__main__":
    input_file = "C:.../letter-recognition.csv"  # Replace with the path to your input CSV file
    output_file = "C:.../normalized_output.csv"  # Replace with the desired output path

    normalize_values(input_file, output_file)
