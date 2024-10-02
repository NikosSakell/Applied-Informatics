import pandas as pd
from sklearn.neighbors import NearestNeighbors
from collections import Counter

def enn(input_file, output_file, k=3):
    # Read the normalized CSV file using pandas
    df = pd.read_csv(input_file)

    # Separate features and class labels
    X = df.iloc[:, :-1].values
    y = df.iloc[:, -1].values

    # Find the k nearest neighbors for each example
    neigh = NearestNeighbors(n_neighbors=k+1)
    neigh.fit(X)
    indices = neigh.kneighbors(X, return_distance=False)[:, 1:]

    # Apply ENN algorithm
    new_indices = []
    for i, neighbors in enumerate(indices):
        neighbor_labels = y[neighbors]
        majority_class = Counter(neighbor_labels).most_common(1)[0][0]
        if y[i] == majority_class:
            new_indices.append(i)

    # Create a new DataFrame with the selected examples
    df_enn = df.iloc[new_indices]

    # Write the edited data to a new CSV file
    df_enn.to_csv(output_file, index=False)

if __name__ == "__main__":
    input_file = r"C:...\letter-recognition-normalized-output.csv"  # Replace with the path to your normalized input CSV file
    output_file = r"C:...\letter-recognition-enn-applied.csv"  # Replace with the desired output path

    enn(input_file, output_file)
