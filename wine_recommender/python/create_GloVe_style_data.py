from collections import defaultdict
import re

def preprocess_text(text):
    # Lowercase and remove punctuation
    text = text.lower()
    text = re.sub(r'[^\w\s]', '', text)
    # Tokenize by splitting on whitespace
    tokens = text.split()
    return tokens

def build_cooccurrence_matrix(tokens, window_size=5):
    # Initialize a default dictionary of dictionaries for word co-occurrences
    cooccurrence_matrix = defaultdict(lambda: defaultdict(int))

    # Populate co-occurrence counts
    for i, token in enumerate(tokens):
        # Look at context window around each word
        start = max(0, i - window_size)
        end = min(len(tokens), i + window_size + 1)

        for j in range(start, end):
            if i != j:  # skip the word itself
                cooccurrence_matrix[token][tokens[j]] += 1

    return cooccurrence_matrix

def print_glove_format(cooccurrence_matrix):
    for word, neighbors in cooccurrence_matrix.items():
        for context_word, count in neighbors.items():
            print(f"{word} {context_word} {count}")

# Example string
text = """
The thing that the girl bought is red but is it round?
"""

# Preprocess and build the matrix
tokens = preprocess_text(text)
cooccurrence_matrix = build_cooccurrence_matrix(tokens)

# Print in GloVe format
print_glove_format(cooccurrence_matrix)
