import base64

def convert_image_to_base64(image_path):
    with open(image_path, "rb") as image_file:
        base64_string = base64.b64encode(image_file.read()).decode('utf-8')
        return f"data:image/png;base64,{base64_string}"

# 示例用法
image_path = "C:\\Users\\86189\\Desktop\\vsco.png"
base64_string = convert_image_to_base64(image_path)
print(base64_string)
