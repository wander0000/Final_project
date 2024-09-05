document.addEventListener('DOMContentLoaded', () => {
    const maleButton = document.getElementById('male');
    const femaleButton = document.getElementById('female');

    maleButton.addEventListener('click', () => {
        maleButton.classList.add('active');
        femaleButton.classList.remove('active');
    });

    femaleButton.addEventListener('click', () => {
        femaleButton.classList.add('active');
        maleButton.classList.remove('active');
    });
});
